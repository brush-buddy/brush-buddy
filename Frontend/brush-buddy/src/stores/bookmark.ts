import { defineStore } from 'pinia';
import { ref } from "vue";
import { localAxios } from '../api/axios';
export const useBookmarksStore = defineStore("bookmarks", () => {

    const isBookmarked = ref(false); // 북마크 상태를 저장하는 변수
    
    async function getBookmarkState(draftId: number) { 
        localAxios().get(`/draft/${draftId}/is-bookmarked`)
        .then((response: any) => {
            isBookmarked.value = response.data; // 북마크 상태를 저장
          })
    }

     async function addBookmark(draftId: number) { 
        localAxios().post(`/draft/${draftId}/bookmark`)
    }

    async function removeBookmark(draftId: number) {
        localAxios().post(`/draft/${draftId}/is-bookmarked`)
        .then((response: any) => {
            isBookmarked.value = response.data; // 북마크 상태를 저장
          })
    }
    
    return {
        isBookmarked,
        getBookmarkState,
        addBookmark,
        removeBookmark
    };
});