import { Rubik } from "next/font/google";
import Navbar from "@/components/Navbar";
import LinearChart from "@/components/LinearChart";
import DatePickerField from "@/components/DatePickerField";
import BarChart from "@/components/BarChart";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";
import React, {useState} from "react";
import Grid from "@mui/material/Grid";
import MetricsCard from "@/components/MetricsCard";

const rubik = Rubik({ subsets: ["cyrillic"] });

export default function Home() {
  const [clas, setClas] = useState(null);
  const isStudent = true;

  return (
    <main
      className={`flex min-h-screen flex-col items-center bg-neutral-900 px-10 space-y-10 pb-20 ${rubik.className}`}
    >
      <Navbar isStudent={isStudent}/>
      {/*<MetricsCard title="this month" attention="8/10"/>*/}
      { isStudent ?
        <div className="flex flex-col items-center justify-between w-full space-y-10 sm:px-80">
          <DatePickerField/>
          <LinearChart title={""} labels={['English', "Maths", "VOT", "IOT", "Biology", "Chemistry", "History"]} userData={[100, 75, 65, 90, 110, 130, 60]}/>
          <BarChart title={""} labels={['English', "Maths", "VOT", "IOT", "Biology", "Chemistry", "History"]} userData={[5, 8, 6, 9, 7, 4, 5]}/>
        </div> :
        <div className="flex flex-col space-y-5">
          <div className="flex items-center w-60">
            <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">Class</InputLabel>
              <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={clas}
                  label="Class"
                  onChange={(newClass) => setClas(newClass)}
              >
                <MenuItem value={"12V"}>12V</MenuItem>
                <MenuItem value={"11B"}>11B</MenuItem>
                <MenuItem value={"10G"}>10G</MenuItem>
              </Select>
            </FormControl>
          </div>
          <div className="flex flex-row">
            <Grid container spacing={10}>
              <Grid item>
                <MetricsCard title="this month" attention={7}/>
              </Grid>
              <Grid item>
                <MetricsCard title="this week" attention={6}/>
              </Grid>
              <Grid item>
                <MetricsCard title="today" attention={7}/>
              </Grid>
            </Grid>
          </div>
        </div>
      }
    </main>
  );
}
