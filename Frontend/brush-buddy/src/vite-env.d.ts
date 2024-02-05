/// <reference types="vite/client" />

interface ImportMetaEnv {

    readonly VITE_APP_SERVER_URL: string,
    readonly VITE_APP_KAKAO_REDIRECT_URL: string,
    readonly VITE_APP_KAKAO_API_JS_KEY: string,
    readonly VITE_APP_KAKAO_API_ADMIN_KEY: string,
    readonly VITE_APP_KAKAO_API_REST_KEY: string
  }
  
  interface ImportMeta {
    readonly env: ImportMetaEnv
  }