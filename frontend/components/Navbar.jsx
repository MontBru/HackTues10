import {useState, useEffect} from "react";
import {IconButton} from "@mui/material";
import LogoutRoundedIcon from '@mui/icons-material/LogoutRounded';
import CircleIcon from '@mui/icons-material/Circle';
import Link from "next/link";
import styles from '../styles/navbar.module.css';
import Image from 'next/image';
import useUserStore from "@/Storages/UserStorage";

export default function Navbar({isStudent, islive}) {
    const [live, setLive] = useState(false);
    const {me, setMe} = useUserStore();

    useEffect(() => {
        setLive(islive)
    }, []);

    return (
        <nav className="flex items-center justify-between w-full h-20">
            <div className="flex space-x-3">
                <Link href="/">
                    <h1 className="text-3xl text-neutral-300">FocusFlow</h1>
                </Link>
                <Image src="/focusflow.png" alt="FocusFlow logo" width={40} height={40}/>
            </div>
            <div className={`${styles.container} ${live ? styles.centered : ''}`}>
                {!isStudent &&
                    <div className="flex items-center space-x-1.5 pr-5">
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
            <div className="flex items-center space-x-5">
                <h1 className="text-xl text-neutral-300" style={{width: "150px",  display: "flex", justifyContent: "end"}}>{me.name}</h1>
                <Link href="/login">
                    <IconButton color="primary">
                        <LogoutRoundedIcon />
                    </IconButton>
                </Link>
            </div>
        </nav>
    );
}