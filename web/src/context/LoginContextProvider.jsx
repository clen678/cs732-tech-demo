import LoginContext from "./LoginContext.jsx"
import { useState, useEffect } from "react";
import api from '../api/axiosConfig'

export function LoginContextProvider({ children }) {

    const [currentUser, setCurrentUser] = useState();
    const [users, setUsers] = useState();

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await api.get("/api/users");
                setUsers(response.data);
                console.log("fetched users:", response.data);
                console.log("current user is:", currentUser);
            } catch (error) {
                console.error("Error fetching users:", error);
            }
        };

        fetchUsers();
    }, [currentUser]);

    const deleteUser = async () => {
        console.log("deleting id", currentUser.username, currentUser._id);
        try {
            const response = await api.delete(`/api/users/${currentUser._id}`);
            setCurrentUser();
        } catch (error) {
            console.error("Error deleting users:", error);
        }
    }

    const contextValue = {
        users,
        currentUser,
        setCurrentUser,
        setUsers,
        deleteUser,
    }

    return (
        <LoginContext.Provider value={contextValue}>
            {children}
        </LoginContext.Provider>
    );
}

export default LoginContextProvider;