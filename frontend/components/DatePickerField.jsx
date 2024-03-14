import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DateField } from '@mui/x-date-pickers/DateField';
import {useState} from "react";

export default function DatePickerField() {
    const [value, setValue] = useState(null);

    return (
        <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DateField
                label="Select Date"
                value={value}
                onChange={(newValue) => setValue(newValue)}
                inputProps={{ style: { color: 'white' } }}
                slotProps={{
                    textField: () => ({
                        color: 'primary',
                        focused: true,
                    }),
                }}
            />
        </LocalizationProvider>
    );
}
