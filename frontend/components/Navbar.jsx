import {useState} from "react";
import {IconButton} from "@mui/material";
import LogoutRoundedIcon from '@mui/icons-material/LogoutRounded';
import CircleIcon from '@mui/icons-material/Circle';
import Link from "next/link";

export default function Navbar({isStudent}) {
    const [name, setName] = useState("Nikola Petrov");

    return (
        <nav className="flex items-center justify-between w-full h-20">
            <h1 className="text-3xl text-neutral-300">Beat box</h1>
            {!isStudent &&
                <div className="flex items-center space-x-1.5">
                    <Link href="/live_page">
                        <p className="text-2xl text-neutral-300">
                            Live
                        </p>
                    </Link>
                    <CircleIcon fontSize="small" color="error"/>
                </div>
            }
            <div className="flex items-center space-x-10">
                <p className="text-xl text-neutral-300">{name}</p>
                <Link href="/login">
                    <IconButton color="primary">
                        <LogoutRoundedIcon />
                    </IconButton>
                </Link>
            </div>
        </nav>
    );
}