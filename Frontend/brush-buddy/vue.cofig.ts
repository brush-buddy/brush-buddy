
module.exports = {
    devServer: {
        proxy: process.env.VITE_SERVER_URL // http://127.0.0.1:17000/api
    }
}