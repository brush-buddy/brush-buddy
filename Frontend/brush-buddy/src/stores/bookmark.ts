import { defineStore } from 'pinia';
import { ref } from "vue";
import { localAxios } from '../api/axios';
export const useBookmarksStore = defineStore("bookmarks", () => {
    const bookmarks = ref(<number[]>[]) // 북마크된 draftId를 저장하는 배열
    const isBookmarked = ref(false); // 북마크 상태를 저장하는 변수
    async function addBookmark(draftId: number) {
        try {
            await addBookmark(draftId); // 북마크 추가 API 호출
            ADD_BOOKMARK(draftId); // 상태를 변경하기 위해 뮤테이션 호출
        } catch (error) {
            console.error('북마크 추가 에러:', error);
        }
    }
    
    async function getBookmarkState(draftId: number) { 
        localAxios().get(`/draft/${draftId}/is-bookmarked`)
        .then((response: any) => {
            isBookmarked.value = response.data; // 북마크 상태를 저장
          })
    }

    async function removeBookmark(draftId: number) {
        try {
            await removeBookmark(draftId); // 북마크 삭제 API 호출
            REMOVE_BOOKMARK(draftId); // 상태를 변경하기 위해 뮤테이션 호출
        } catch (error) {
            console.error('북마크 삭제 에러:', error);
        }
    }
    
    function ADD_BOOKMARK(draftId: number) {
        bookmarks.value.push(draftId); // 북마크 배열에 draftId 추가
    }
    
    function REMOVE_BOOKMARK(draftId: number) {
        bookmarks.value = bookmarks.value.filter(id => id !== draftId); // 북마크 배열에서 해당 draftId 제거
    }
    
    return {
        bookmarks,
        isBookmarked,
        getBookmarkState,
        addBookmark,
        removeBookmark
    };
});