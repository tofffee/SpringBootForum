package com.example.springforumapp.publications.services;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.files.models.dto.UpFileOutDTO;
import com.example.springforumapp.files.services.UpFileServiceImpl;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.models.dto.PublicationInDTO;
import com.example.springforumapp.publications.models.dto.PublicationOutDTO;
import com.example.springforumapp.publications.repositories.PublicationsRepository;
import com.example.springforumapp.publications.util.exceptions.PublicationDeleteException;
import com.example.springforumapp.publications.util.exceptions.PublicationNotFoundException;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.models.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class PublicationsServiceImpl implements PublicationService {
    private final PublicationsRepository publicationsRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<PublicationOutDTO> findAllPublicationsByPage(int pageNum,
                                                             int pageSize,
                                                             String sortType,
                                                             String sortBy) throws IllegalArgumentException{
        List<Publication> publications = publicationsRepository.findAll(PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.fromString(sortType),sortBy))).getContent();
        return publicationsToOutDTOs(publications);
    }
    @Override
    public List<PublicationOutDTO> findAllPublicationsInBoardByPage(Board board,
                                                                    int pageNum,
                                                                    int pageSize,
                                                                    String sortType,
                                                                    String sortBy) throws IllegalArgumentException {

        List<Publication> publications = publicationsRepository.findAllByBoardId(board.getId(), PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.fromString(sortType),sortBy))).getContent();
        return publicationsToOutDTOs(publications);
    }
    @Override
    public PublicationOutDTO findPublicationByIdInBoard(Board board, long publicationId) throws PublicationNotFoundException{
        Optional<Publication> publication = publicationsRepository.findByIdAndBoardId(publicationId, board.getId());
        if(publication.isEmpty())
            throw new PublicationNotFoundException("Such publication does not exist",
                                                   "PublicationService.java: PublicationNotFoundException");
        return publicationToOutDTO(publication.get());
    }

    @Override
    @Transactional
    public PublicationOutDTO savePublication(User user, Board board, PublicationInDTO inDto, List<UpFile> upFiles){
        Publication publication = convertInDtoToPublication(inDto);
        publication.setUser(user);
        publication.setBoard(board);
        publication.setUpfiles(upFiles);
        publication.setCreatedAt(LocalDate.now());
        publicationsRepository.save(publication);
        return publicationToOutDTO(publication);
    }

    @Override
    @Transactional
    public void deletePublication(User user, long publicationId) throws PublicationNotFoundException , PublicationDeleteException{
        Optional<Publication> publication = publicationsRepository.findById(publicationId);
        if (publication.isEmpty()){
           throw new PublicationNotFoundException("Such publication does not exist","user write id of not exist publication");
        }
        if (user.getId() == publication.get().getUser().getId()) {
            publicationsRepository.delete(publication.get());
        } else {
            throw new PublicationDeleteException("It is not your publication", "user tries to delete foreign publication");
        }
    }


    private PublicationOutDTO publicationToOutDTO(Publication publication){
        PublicationOutDTO dto = new PublicationOutDTO();
        dto.setId(publication.getId());
        dto.setUserDTO(modelMapper.map(publication.getUser(), UserDTO.class));
        dto.setName(publication.getName());
        dto.setText(publication.getText());
        dto.setCreatedAt(publication.getCreatedAt());
        if(publication.getUpfiles() != null && !publication.getUpfiles().isEmpty())
            dto.setUpFilesOutDtos(modelMapper.map(publication.getUpfiles(),new TypeToken<List<UpFileOutDTO>>(){}.getType()));
        else
            dto.setUpFilesOutDtos(new ArrayList<>());
        return dto;
    }

    private List<PublicationOutDTO> publicationsToOutDTOs(List<Publication> publications){
        return publications
                .stream()
                .map(this::publicationToOutDTO)
                .collect(Collectors.toList());
    }

    private Publication convertInDtoToPublication(PublicationInDTO dto){
        Publication publication = new Publication();
        publication.setName(dto.getName());
        publication.setText(dto.getText());
        return publication;
    }
}
