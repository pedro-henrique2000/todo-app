import { createContext, useState } from 'react'

export const AuthContext = createContext({})

export function AuthProvider({children}) {
    const user = JSON.parse(localStorage.getItem('user'))
    const [isLogged, setIsLogged] = useState(user ? true : false)

    function createToken(username, password) {
        const token = 'Basic ' +  btoa(username + ':' + password);
        localStorage.setItem('user', JSON.stringify({ token, username }))
        setIsLogged(true)
    }

    function logout() {
        setIsLogged(false)
        localStorage.removeItem('user')
    }

    return (
        <AuthContext.Provider value={{
            isLogged,
            createToken,
            logout
        }}>
            {children}
        </AuthContext.Provider>
    )

}