import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import {DatePicker} from "@mui/x-date-pickers";

export default function DatePickerField({date, setDate}) {
    return (
        <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DatePicker
                label="Select Date"
                value={date}
                format="DD/MM/YYYY"
                onChange={(newValue) => setDate(newValue)}
                inputProps={{ style: { color: 'white' } }}
                slotProps={{
                    textField: () => ({
                        color: 'primary',
                        focused: true,
                    }),
                    openPickerButton: {
                        color: 'primary',
                    },
                }}

            />
        </LocalizationProvider>
    );
}
