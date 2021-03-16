import axios from 'axios'

const httpClient = axios.create({
    baseURL: 'http://localhost:8080'
})

export function HelloApi() {
    const {token} = JSON.parse(localStorage.getItem('user'))

    async function hello() {
        const response = await httpClient.get("/helloApi", {
            headers: {
                'Authorization' : token 
            }
        })

        return response
    }

    return {hello}
}