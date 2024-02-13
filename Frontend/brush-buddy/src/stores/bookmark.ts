import { defineStore } from 'pinia';
import { ref } from "vue";
import { localAxios } from '../api/axios';

const useBookmarksStore = defineStore("bookmarks", () => {
    const isBookmarked = ref(false); // 북마크 상태를 저장하는 변수
    
    function setBookmarkState(bookmarkState: boolean) {
        isBookmarked.value = bookmarkState; // 북마크 상태를 저장
    }

    // async function getBookmarkState(draftId: number) { 
    //     localAxios().get(`/draft/${draftId}/is-bookmarked`)
    //     .then((response: any) => {
    //         isBookmarked.value = response.data; // 북마크 상태를 저장
    //       })
    // }

    function addBookmark(draftId: number) { 
         localAxios().post(`/draft/${draftId}/bookmark`)
        .then(() => {
           isBookmarked.value = true;
          })
         
    }

    function removeBookmark(draftId: number) {
        localAxios().delete(`/draft/${draftId}/bookmark`)
        .then(() => {
            isBookmarked.value = false // 북마크 상태를 저장
          })
    }
    
    return {
        isBookmarked,
        setBookmarkState,
        // getBookmarkState,
        addBookmark,
        removeBookmark
    };
});

export { useBookmarksStore }