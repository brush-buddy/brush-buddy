import {defineStore} from "pinia";

export const useStoreTodo = defineStore("boardList", {
  state: () => ({
    boardList : [],
  }),
  getters: {},
  actions: {},
  },
});