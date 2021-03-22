import React from 'react'
import './style.css'
import { Link } from 'react-router-dom'

export function HomeScreen() {
    return (
        <div className="home-container">
            <div className="home-info">
                <img src="home.jpg" alt="Home"/>
                <div className="home-content">
                    <h1>Todo App</h1>
                    <p>
                        Lorem Ipsum is simply dummy text of the printing and 
                        typesetting industry. Lorem Ipsum has been the industry's 
                        standard dummy text ever since the 1500s, when an unknown 
                        printer took a galley of type and scrambled it to make a 
                        type specimen book.
                    </p>
                    <Link to="/login" className="home-button">Sign in!</Link>
                </div>
            </div>
        </div>
    )
}