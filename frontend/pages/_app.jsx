import "@/styles/globals.css";
import { createTheme, ThemeProvider } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    primary: {
      main: '#D4D4D4'
    },
    background: {
      main: '#111827'
    }
  },
});

export default function App({ Component, pageProps }) {

  return (
      <ThemeProvider theme={theme}>
        <Component {...pageProps} />
      </ThemeProvider>
  );
}
