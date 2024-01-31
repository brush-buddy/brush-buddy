package com.a205.brushbuddy.mypage.service;


import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.draft.domain.Draft;
import com.a205.brushbuddy.mypage.dto.response.MypageBookmarkedDraftListResponseDto;
import com.a205.brushbuddy.mypage.dto.response.MypageGeneratedDraftListResponseDto;
import com.a205.brushbuddy.mypage.dto.response.MypageHeartBoardListResponseDto;
import com.a205.brushbuddy.mypage.dto.response.MypagePurchasedDraftListResponseDto;
import com.a205.brushbuddy.mypage.repository.MypageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService{
    private final MypageRepository mypageRepository;

    @Override
    public MypageHeartBoardListResponseDto getHeartBoardList(Integer userId, String search, Pageable pageable) { // 좋아요 누른 게시글 리스트 조회
       Page<Board> result =  mypageRepository.getHeartBoardList(userId, search, pageable);
        return MypageHeartBoardListResponseDto.builder()
                .pageNum(result.getNumber()+1)
                .length(result.getNumberOfElements())
                .totalPage(result.getTotalPages())
                .boards(result.getContent().stream().map( m-> MypageHeartBoardListResponseDto.BoardDTO.builder()
                                .boardId(m.getBoardId())
                                .views(m.getBoardWatch())
                                .thumbnail(m.getBoardThumbnail())
                                .likeNumber(m.getBoardLikeNumber())
                                .boardTitle(m.getBoardTitle())
                                .createdAt(m.getBoardTimestamp().toString())
                        .build())
                        .toList())
                .build();
    }

    @Override
    public MypageGeneratedDraftListResponseDto getGeneratedBoardList(Integer userId, String search, Pageable pageable){ // 생성한 도안 리스트 조회
        Page<Draft> result =  mypageRepository.getGeneratedDraftList(userId, search, pageable);
        return MypageGeneratedDraftListResponseDto.builder()
                .pageNum(result.getNumber()+1)
                .length(result.getNumberOfElements())
                .totalPage(result.getTotalPages())
                .drafts(result.getContent().stream().map(m-> MypageGeneratedDraftListResponseDto.DraftDto.builder()
                                .draftId(m.getDraftId())
                                .draftThumbnail(m.getDraftThumbnail())
                                .draftTimestamp(m.getDraftTimestamp())
                        .build())
                        .toList())
                .build();
    }

    @Override
    public MypageBookmarkedDraftListResponseDto getBookmarkedBoardList(Integer userId,String search, Pageable pageable) {// 북마크한 도안 리스트 조회
        Page<Draft> result =  mypageRepository.getBookmarkedDraftList(userId, search, pageable);
        return MypageBookmarkedDraftListResponseDto.builder()
                .pageNum(result.getNumber()+1)
                .length(result.getNumberOfElements())
                .drafts(result.getContent().stream().map(m-> MypageBookmarkedDraftListResponseDto.DraftDto.builder()
                                .draftId(m.getDraftId())
                                .draftThumbnail(m.getDraftThumbnail())
                                .draftTimestamp(m.getDraftTimestamp())
                                .draftBookmark(m.getDraftBookmark())
                                .draftDownload(m.getDraftDownload())
                                .build())
                        .toList())
                .totalPage(result.getTotalPages())
                .build();
    }

    @Override
    public MypagePurchasedDraftListResponseDto getPurchasedDraftList(Integer userId,String search, Pageable pageable){//구매한 도안 내역 리스트 조회
        Page<Draft> result =  mypageRepository.getPurchasedDraftList(userId, search, pageable);
        return MypagePurchasedDraftListResponseDto.builder()
                .pageNum(result.getNumber()+1)
                .length(result.getNumberOfElements())
                .totalPage(result.getTotalPages())
                .drafts(result.getContent().stream().map(m-> MypagePurchasedDraftListResponseDto.DraftDto.builder()
                                .draftId(m.getDraftId())
                                .draftThumbnail(m.getDraftThumbnail())
                                .draftTimestamp(m.getDraftTimestamp())
                                .draftBookmark(m.getDraftBookmark())
                                .draftDownload(m.getDraftDownload())
                                .build())
                        .toList())
                .build();
    }
}
