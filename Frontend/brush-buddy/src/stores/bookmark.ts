import { defineStore } from 'pinia';
import { ref } from "vue";
import { localAxios } from '../api/axios';

const useBookmarksStore = defineStore("bookmarks", () => {
    const isBookmarked = ref(false); // 북마크 상태를 저장하는 변수
    const totalBookmarkNum = ref<number>(0);
    function setBookmarkState(bookmarkState: boolean, bookmarkNum: number) {
        isBookmarked.value = bookmarkState; // 북마크 상태를 저장
        totalBookmarkNum.value = bookmarkNum;
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
        totalBookmarkNum.value += 1
         
    }

    function removeBookmark(draftId: number) {
        localAxios().delete(`/draft/${draftId}/bookmark`)
        .then(() => {
            isBookmarked.value = false // 북마크 상태를 저장
        })
        totalBookmarkNum.value -= 1
    }
    
    return {
        isBookmarked,
        totalBookmarkNum,
        setBookmarkState,
        // getBookmarkState,
        addBookmark,
        removeBookmark
    };
});

export { useBookmarksStore }