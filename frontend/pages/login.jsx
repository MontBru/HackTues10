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
import Authentication from '@/services/Authentication';

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
  

  
  

export default function Login() {
  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
  
    try {
      await Authentication({ email: data.get('email'), password: data.get('password') });
      console.log({
        email: data.get('email'),
        password: data.get('password'),
      });
    } catch (error) {
      console.error(error);
    }
  };

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
            Sign in
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
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
            <Button
              type="submit"
              fullWidth
              variant="outlined"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign In
            </Button>
            <Link href="/register" variant="body2">
                {"Don't have an account? Sign Up"}
            </Link>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}