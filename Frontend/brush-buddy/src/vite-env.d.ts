/// <reference types="vite/client" />

interface ImportMetaEnv {
    readonly VITE_APP_KAKAO_REDIRECT_URL: string
    readonly VITE_APP_KAKAO_API_JS_KEY: string
    readonly VITE_APP_KAKAO_API_ADMIN_KEY: string
    readonly VITE_APP_KAKAO_API_REST_KEY: string
    // 다른 환경 변수들에 대한 타입 정의...
  }
  
  interface ImportMeta {
    readonly env: ImportMetaEnv
  }