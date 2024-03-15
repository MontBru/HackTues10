import { Rubik } from "next/font/google";
import Navbar from "@/components/Navbar";
import LinearChart from "@/components/LinearChart";
import DatePickerField from "@/components/DatePickerField";
import BarChart from "@/components/BarChart";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";
import React, {useState} from "react";
import Grid from "@mui/material/Grid";
import MetricsCard from "@/components/MetricsCard";
import ItemsList from "@/components/ItemsList";
import ChangeEvalPopup from "@/components/ChangeEvalPopup";

const rubik = Rubik({ subsets: ["cyrillic"] });

export default function Home() {
  const [clas, setClas] = useState(null);
  const isStudent = false;

  const [showPopup, setShowPopup] = useState(false);
  const mockData = [{subjectName:"Maths", entryIds: [5,1], attention: 5},{subjectName:"BEL", entryIds: [2,4], attention: 6}, {subjectName:"AE", entryIds: [3,6,7], attention: 4}]



  return (
    <main
      className={`flex min-h-screen flex-col items-center bg-neutral-900 px-10 space-y-10 pb-20 ${rubik.className}`}
    >
      <Navbar isStudent={isStudent}/>
      <ChangeEvalPopup isOpen={showPopup} onClose={()=>{setShowPopup(false)}} evaluations={mockData}/>
      <button className="bg-white" onClick={()=>{setShowPopup(true)}}>
        Change Eval
      </button>
      { isStudent ?
        <div className="flex flex-col items-center justify-between w-full space-y-10 sm:px-80">
          <DatePickerField/>
          <LinearChart title={""} labels={['English', "Maths", "VOT", "IOT", "Biology", "Chemistry", "History"]} userData={[100, 75, 65, 90, 110, 130, 60]} yAxisText="beats per minute" xAxisText="subjects"/>
          <BarChart title={""} labels={['English', "Maths", "VOT", "IOT", "Biology", "Chemistry", "History"]} userData={[5, 8, 6, 9, 7, 4, 5]}  yAxisText="attention" xAxisText="subjects"/>
        </div> :
          <div className="flex flex-col space-y-5">
            <div className="flex flex-col items-center space-y-5">
              <p className="text-3xl text-neutral-300">Student's attention</p>
              <div className="w-60 pb-10">
                <FormControl fullWidth>
                  <InputLabel id="demo-simple-select-label">Class</InputLabel>
                  <Select
                      labelId="demo-simple-select-label"
                      id="demo-simple-select"
                      value={clas}
                      label="Class"
                      autoFocus
                      focused={true}
                      onChange={(newClass) => setClas(newClass)}
                  >
                    <MenuItem value={"12V"}>12V</MenuItem>
                    <MenuItem value={"11B"}>11B</MenuItem>
                    <MenuItem value={"10G"}>10G</MenuItem>
                  </Select>
                </FormControl>
              </div>
            </div>
            <div className="flex flex-row pb-10">
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
            <div className="flex items-center space-x-20">
              <ItemsList list={["Nikola Petrov 12V", "Ivan Postolov 12V", "Bryan Monticelli 12V", "Stefan Georgiev 11V", "Kaloyan Sotirov 12V", "Nikola Petrov 12V", "Ivan Postolov 12V", "Bryan Monticelli 12V", "Stefan Georgiev 11V", "Kaloyan Sotirov 12V"]}/>
              <div className="w-full">
                <BarChart title={""} labels={[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]} userData={[5, 0, 8, 6, 9, 7, 0, 4, 5, 0]} yAxisText="Number of students" xAxisText="attention"/>
              </div>
            </div>
          </div>
      }
    </main>
  );
}
