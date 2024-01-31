import { createPinia } from "pinia";
const pinia = createPinia();

export default pinia;


interface boardListItem {
    id: number;
    title: string;
    board_id: number;
    like: boolean;
}
