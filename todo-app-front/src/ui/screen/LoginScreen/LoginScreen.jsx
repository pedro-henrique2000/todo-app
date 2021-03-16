import React, { useState } from 'react'
import { useHistory } from 'react-router'
import { AuthApi } from '../../../hooks/auth/auth.hook'

export function LoginScreen() {
    const auth = AuthApi()
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    // const [message, setMessage] = useState('')

    let history = useHistory()

    async function handleSubmit(event) {
        event.preventDefault()
        try {
            const { email } = await auth.login(username, password)
            const token = `Basic ${window.btoa(email + ':' + password)}`
            localStorage.setItem('user', JSON.stringify({ token, email }))
            history.push("/hello")
        } catch (err) {
            console.log(err)
        }
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <label htmlFor="username">
                    <input type="text" name="username" value={username} onChange={(event) => setUsername(event.target.value)} />
                </label>
                <br />
                <label htmlFor="password">
                    <input type="password" name="password" value={password} onChange={(event) => setPassword(event.target.value)} />
                </label>
                <button type="submit">Login</button>
            </form>
        </>
    )

}