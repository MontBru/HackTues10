import "@/styles/globals.css";
import { createTheme, ThemeProvider } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    type: 'dark',
    primary: {
      main: '#D4D4D4',
    },
    background: {
      default: '#121212',
      paper: '#1E1E1E',
    },
    text: {
      primary: '#FFFFFF',
      secondary: '#B0B0B0',
    },
    action: {
      hover: '#424242',
    },
  },
});


export default function App({ Component, pageProps }) {

  return (
      <ThemeProvider theme={theme}>
        <Component {...pageProps} />
      </ThemeProvider>
  );
}