import React, { useEffect, useState } from 'react'
import { Input } from '../../component/Input/Input.component'
import { AuthApi } from '../../../hooks/auth/auth.hook'

export function RegisterScreen() {
    const [email, setEmail] = useState('')
    const [name, setName] = useState('')
    const [password, setPassword] = useState('')
    const [confirmPassword, setConfirmPassword] = useState('')
    const [mensagem, setMensagem] = useState('')
    const [erro, setErro] = useState('')

    const auth = AuthApi()

    useEffect(() => { }, [mensagem, erro])

    async function handleSubmit(event) {
        event.preventDefault()

        try {
            await auth.register(email, password, confirmPassword, name)
            setErro('')
            setMensagem("Cadastrado com Sucesso")
        } catch (err) {
            setMensagem('')
            setErro(err.response.data.message)
        }
    }


    return (
        <div className="form-container">
            <header>
                <h2>Welcome!</h2>
            </header>
            <main>
                <form onSubmit={handleSubmit}>
                    <Input
                        type="text"
                        name="Enter your Email"
                        label="Email"
                        handleChange={setEmail}
                        value={email}
                    />

                    <Input
                        type="text"
                        name="Enter your Name"
                        label="Name"
                        handleChange={setName}
                        value={name}
                    />

                    <Input
                        type="password"
                        name="Enter your Password"
                        label="Password"
                        handleChange={setPassword}
                        value={password}
                    />

                    <Input
                        type="password"
                        name="Confirm Password"
                        label="Confirm Password"
                        handleChange={setConfirmPassword}
                        value={confirmPassword}
                    />
                    {mensagem ? <p className="form-success">{mensagem}</p> : erro && <p className="form-alert">{erro}</p>}
                    <button className="button">Register</button>
                </form>
            </main>
        </div>

    )
}