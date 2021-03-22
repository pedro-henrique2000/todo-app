import React, { useContext } from 'react'
import './style.css'
import { AuthContext } from '../../../context/AuthContext/AuthContext'
import { useHistory } from 'react-router'
import { Link } from 'react-router-dom'

export function Header() {
    const { isLogged, logout, name } = useContext(AuthContext)

    let history = useHistory()

    function handleLogout() {
        logout()
        history.push("/login")
    }

    function handleReturn() {
        history.goBack()
    }

    return (
        <header className="header-container">
            <img src="previous.png" className="return" onClick={handleReturn} alt="Return"/>
            <Link className="header-logo" to="/">
                <img src="./icon.png" alt="Logo do Site" />
                <span>ToDo App</span>
            </Link>
            <div className="header-info">
                {isLogged ?
                    <div className="header-logged">
                        <p>Bem-Vindo {name} | </p>
                        <button onClick={handleLogout}>Logout</button>
                    </div>
                    :
                    <div className="header-buttons">
                        <Link to="/register">Sign up | </Link>
                        <Link to="/login"> Sign in</Link>
                    </div>
                }
            </div>
        </header>
    )
}