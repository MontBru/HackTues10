import {useState, useEffect} from "react";
import {IconButton} from "@mui/material";
import LogoutRoundedIcon from '@mui/icons-material/LogoutRounded';
import CircleIcon from '@mui/icons-material/Circle';
import Link from "next/link";
import styles from '../styles/navbar.module.css'

export default function Navbar({isStudent, islive}) {
    const [name, setName] = useState("Nikola Petrov");
    const [live, setLive] = useState(false)

    useEffect(() => {

        setLive(islive)
    }, []);

    return (
        <nav className="flex items-center justify-between w-full h-20">
            <Link href="/">
                <h1 className="text-3xl text-neutral-300">FocusFlow</h1>
            </Link>
            <div className={`${styles.container} ${live ? styles.centered : ''}`}>
                {!isStudent &&
                    <div className="flex items-center space-x-1.5">
                        <Link href={`${live ? "/":"/live"}`}>
                            <p className="text-2xl " style={{color: `${live ? "#d4d4d4" : "grey"}`}}>
                                Live
                            </p>
                        </Link>
                        {
                            live &&
                            <CircleIcon fontSize="small" color={live? "error" : ""}/>
                        }
                        
                    </div>
                }
            </div>
            <div className="flex items-center space-x-10">
                <h1 className="text-xl text-neutral-300" style={{width: "150px",  display: "flex", justifyContent: "end"}}>{name}</h1>
                <Link href="/login">
                    <IconButton color="primary">
                        <LogoutRoundedIcon />
                    </IconButton>
                </Link>
            </div>
        </nav>
    );
}