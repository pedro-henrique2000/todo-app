import React, { useContext, useState } from 'react'
import { useHistory } from 'react-router'
import { AuthApi } from '../../../hooks/auth/auth.hook'
import { Input } from '../../component/Input/Input.component'
import { Link } from 'react-router-dom'
import './style.css'
import { AuthContext } from '../../../context/AuthContext/AuthContext'
import { Redirect } from 'react-router';

export function LoginScreen() {
    const auth = AuthApi()
    const { isLogged, createToken } = useContext(AuthContext)
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [erro, setErro] = useState('')

    let history = useHistory()

    if (isLogged) {
        return <Redirect to="/tasks" />
    }

    async function handleSubmit(event) {
        event.preventDefault()
        try {
            const { email } = await auth.login(username, password)
            createToken(username, password)
            history.push("/tasks")
        } catch (err) {
            setErro(err.response.data.message)
        }
    }

    return (
        <div className="form-container">
            <header>
                <p>Welcome Back!</p>
            </header>
            <main>
                <form onSubmit={handleSubmit}>
                    <Input
                        type="text"
                        name="username"
                        label="username"
                        handleChange={setUsername}
                        value={username}
                    />

                    <Input
                        type="password"
                        name="Password"
                        label="Password"
                        handleChange={setPassword}
                        value={password}
                    />
                    {erro && <p className="form-alert">{erro}</p>}
                    <button>Login</button>
                </form>
            </main>

            <footer>New Here? <Link to="/register" className="sign-up">Sign Up</Link></footer>
        </div>

    )

}