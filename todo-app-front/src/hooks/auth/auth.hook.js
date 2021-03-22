import axios from 'axios'

const httpClient = axios.create({
    baseURL: 'http://localhost:8080'
})

export function AuthApi() {
    async function login(email, password) {
        const response = await httpClient.post("/authenticate", { email, password })
        return response.data
    }

    async function register(email, password, confirmPassword, name) {
        const response = await httpClient.post("/register", { email, password, confirmPassword, name })
        return response.data
    }

    return { login, register }
}