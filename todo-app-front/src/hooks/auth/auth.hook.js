import axios from 'axios'

const httpClient = axios.create({
    baseURL: 'http://localhost:8080'
})

export function AuthApi() {
    async function login(email, password) {
        const response = await httpClient.post("/authenticate", {email, password})
        return response.data
    }

    return {login}
}