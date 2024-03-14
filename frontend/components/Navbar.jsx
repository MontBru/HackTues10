import PersonIcon from '@mui/icons-material/Person';
import {IconButton} from "@mui/material";

export default function Navbar() {
    return (
        <nav className="flex items-center justify-between w-full h-20 px-10">
            <h1 className="text-2xl text-neutral-300">App name</h1>
            <IconButton aria-label="fingerprint" color="primary" size="large">
                <PersonIcon />
            </IconButton>
        </nav>
    );
}