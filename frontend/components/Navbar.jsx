import {useState} from "react";
import {IconButton} from "@mui/material";
import LogoutRoundedIcon from '@mui/icons-material/LogoutRounded';
import Link from "next/link";

export default function Navbar() {
    const [name, setName] = useState("Nikola Petrov");

    return (
        <nav className="flex items-center justify-between w-full h-20">
            <h1 className="text-3xl text-neutral-300">Beat box</h1>
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