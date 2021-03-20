import React, { useContext } from 'react'
import './style.css'
import { AuthContext } from '../../../context/AuthContext/AuthContext'
import { useHistory } from 'react-router'
import { Link } from 'react-router-dom'

export function Header() {
    const { isLogged, logout } = useContext(AuthContext)

    let history = useHistory()

    function handleLogout() {
        logout()
        history.push("/login")
    }

    return (
        <header className="header-container">
            <div className="header-logo">
                <img src="./icon.png" alt="Logo do Site" />
                <span>ToDo App</span>
            </div>
            <div className="header-info">
                {isLogged ?
                    <>
                        <p>Olá, Pedro</p>
                        <button onClick={handleLogout}>Logout</button>
                    </>
                    :
                    <Link to="/login">Logar</Link>
                }
            </div>
        </header>
    )
}