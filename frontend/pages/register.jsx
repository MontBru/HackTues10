import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Switch } from '@mui/material';
import MultipleSelectChip from '@/components/ChipSelect';
import Registration from '@/services/Authentication/Registration';
import { useRouter } from 'next/router';
import useUserStore from '@/Storages/UserStorage';

const theme = createTheme({
    palette: {
        type: 'dark', // Setting the theme type to dark mode
        primary: {
        main: '#D4D4D4',
        },
        background: {
        default: '#121212', // Dark background color
        paper: '#1E1E1E', // Dark paper color for surfaces
        },
        text: {
        primary: '#FFFFFF', // White text color for primary elements
        secondary: '#B0B0B0', // Lighter text color for secondary elements
        },
        action: {
        hover: '#424242', // Background color when button is hovered
        },
        // Adjusting button colors for better visibility
        button: {
        contained: {
            backgroundColor: '#FFFFFF', // White background color for contained buttons
            color: '#000000', // Black text color for contained buttons
        },
        switch:{
            backgroundColor:'#F43454',
            color:'#F43454'
        }
        },
        // Adjusting TextField colors and borders
        components: {
        MuiTextField: {
            styleOverrides: {
            root: {
                '& .MuiOutlinedInput-root': {
                '& fieldset': {
                    borderColor: '#B0B0B0', // Light border color for TextField
                },
                },
            },
            },
        },
        },
        // Add other customizations as needed
    },
    });

export default function Register(){
    const router = useRouter();
    const {me, setMe} = useUserStore(); 
    const handleSubmit = async (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);

        const email = data.get('email');
        const password = data.get('password');
        const name = data.get('name');
        let classs = [];
        let grades = []; 
        let device_id = 0;
        let class_num = 0; 
        if(isTeacher)
        {
            console.log({email, password, name, classes});
            classes.map((c) => {
                const [clas, grade] = c.split(" ");
                // classs.push(clas)
                // grades.push(grade)
                classs.push({
                    klas : clas,
                    grade : grade
                })
            })
        }
        else
        {
            device_id = data.get('device_id');
            class_num = data.get('classNum');
            // classs = data.get('class');
            // grades = data.get('grade');
            classs.push({
                klas: data.get('class'),
                grade: data.get('grade')
            })
            console.log({email, password, name, device_id, class_num, classs, grades})
        }
        const role = isTeacher ? 0 : 1
        const data1 = await Registration(name, email, password, class_num, device_id, classs, role)
        sessionStorage.setItem("jwtAccess", data1.token)
        setMe(data1.user)
        router.push("http://localhost:3000/")
    
    };

    const [isTeacher, setIsTeacher] = React.useState(false)
    const [classes, setClasses] = React.useState([])

    console.log(isTeacher)
    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Box
                sx={{
                    marginTop: 8,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                    justifyItems: 'center',
                }}
                >
                <Avatar sx={{ m: 1, bgcolor: 'primary.main' }}>
                    <LockOutlinedIcon />
                </Avatar>
                <Typography component="h1" variant="h5">
                    Sign up
                </Typography>
                <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                    <div className='flex flex-row justify-center items-center'>
                        <div>
                            Student
                        </div>
                        <Switch color="default" onChange={()=>setIsTeacher(!isTeacher)}/>
                        <div>
                            Teacher
                        </div>
                    </div>
                    
                    <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="email"
                    label="Email Address"
                    name="email"
                    autoComplete="email"
                    focused={true}
                    autoFocus
                    />
                    <TextField
                    margin="normal"
                    required
                    fullWidth
                    name="password"
                    label="Password"
                    type="password"
                    id="password"
                    focused={true}
                    />
                    <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="name"
                    label="Name & Surname"
                    name="name"
                    autoComplete="name"
                    focused={true}
                    autoFocus
                    />

                    {
                        isTeacher?
                        <>
                            <MultipleSelectChip personName={classes} setPersonName={setClasses}/>
                        </>:
                        <div>
                            <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="device_id"
                            label="Device ID"
                            name="device_id"
                            autoComplete="device_id"
                            focused={true}
                            autoFocus
                            />
                            <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="classNum"
                            label="Class Number"
                            name="classNum"
                            autoComplete="classNum"
                            focused={true}
                            autoFocus
                            />
                            <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="class"
                            label="Class"
                            name="class"
                            autoComplete="class"
                            focused={true}
                            autoFocus
                            />

                            <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="Grade"
                            label="Grade"
                            name="grade"
                            autoComplete="grade"
                            focused={true}
                            autoFocus
                            />
                        </div>
                    }

                    <Button
                    type="submit"
                    fullWidth
                    variant="outlined"
                    sx={{ mt: 3, mb: 2 }}
                    >
                    Sign Up
                    </Button>
                    <Link href="/login" variant="body2">
                        {"Have an account? Sign In"}
                    </Link>
                </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
}