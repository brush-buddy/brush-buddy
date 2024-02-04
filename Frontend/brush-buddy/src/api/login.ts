import axios from "axios";

const kakaoHeader = {
    'Authorization': '---------Admin 키-----------------', // TODO: 키 삽입
    'Content-type': 'application/x-www-form-urlencoded;charset=utf-8',
};

const getKakaoToken = async (code : string) => {
    console.log('loginWithKakao');
    try {
        const data = {
            grant_type: 'authorization_code',
            client_id: '---------------REST API 키------------------', // TODO: 키 삽입
            redirect_uri: 'http://localhost:5173/',
            code: code,
        };
        const queryString = Object.keys(data)
            .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(data[k]))
            .join('&');

        console.log(queryString)
        console.log(('https://kauth.kakao.com/oauth/token', queryString, { headers: kakaoHeader }))
        const result = await axios.post(`https://kauth.kakao.com/oauth/token`, queryString, { headers: kakaoHeader });

        console.log('카카오 토큰', queryString);
        console.log(result)
        return result;
    } catch (e) {
        return e;
    }
};

const refreshToken = async () => {
    try {
        const { result } = (await axios.get('/refreshToken')).data;
        VueCookies.set('access-token', result.access_token);
        console.log('Refresh API 성공', result);
        return result;
    } catch (e) {
        console.log(e);
    }
}

export {
    getKakaoToken,
    refreshToken,
};