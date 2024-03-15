import Navbar from "@/components/Navbar";
import LinearChart from "@/components/LinearChart";
import DatePickerField from "@/components/DatePickerField";
import BarChart from "@/components/BarChart";
import {CircularProgress, FormControl, InputLabel, MenuItem, Select} from "@mui/material";
import {useEffect, useState} from "react";
import Grid from "@mui/material/Grid";
import MetricsCard from "@/components/MetricsCard";
import ItemsList from "@/components/ItemsList";
import Box from "@mui/material/Box";

export default function Home() {
  const [clas, setClas] = useState("");
  const [students, setStudents] = useState([]);
  const [date, setDate] = useState("");
  const [metricThisMonth, setMetricThisMonth] = useState(0);
  const [metricThisWeek, setMetricThisWeek] = useState(0);
  const [metricThisDay, setMetricThisDay] = useState(0);
  const [isStudent, setIsStudent] = useState(null);
  const [pulseData, setPulseData] = useState([]);
  const [studentAttentionData, setStudentAttentionData] = useState([]);
  const [subjects, setSubjects] = useState([]);
  const [classes, setClasses] = useState(["12 v", "11 V", "12 A", "10 G"]);
  const [classAttention, setClassAttention] = useState([]);
  const attentionDefaultValues = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  useEffect(() => {
    setIsStudent(false);
    setMetricThisMonth(7);
    setMetricThisWeek(6);
    setMetricThisDay(7);
    setPulseData([100, 75, 65, 90, 110, 130, 70]);
    setStudentAttentionData([5, 8, 6, 9, 7, 4, 5]);
    setSubjects(['English', "Maths", "VOT", "IOT", "Biology", "Chemistry", "History"]);
    setClasses(["12 v", "11 V", "12 A", "10 G"]);
    setClassAttention([5, 0, 8, 6, 9, 7, 0, 4, 5, 0]);
    setStudents(["Nikola Petrov 12V", "Ivan Postolov 12V", "Bryan Monticelli 12V", "Stefan Georgiev 11V", "Kaloyan Sotirov 12V", "Nikola Petrov 12V", "Ivan Postolov 12V", "Bryan Monticelli 12V", "Stefan Georgiev 11V", "Kaloyan Sotirov 12V"]);
  }, []);

  if(isStudent === null){
    return (
        <div className="flex justify-center items-center min-h-screen bg-neutral-900">
          <Box sx={{ display: 'flex', color: 'grey.500' }}>
            <CircularProgress color="inherit" />
          </Box>
        </div>
    );
  }

  return (
    <main
      className={`flex min-h-screen flex-col items-center bg-neutral-900 px-10 space-y-10 pb-20`}
    >
      <Navbar isStudent={isStudent} islive={false}/>
      { isStudent ?
        <div className="flex flex-col items-center justify-between w-full space-y-10 sm:px-80">
          <DatePickerField date={date} setDate={setDate}/>
          <LinearChart
              title={"Pulse chart"}
              labels={subjects}
              userData={pulseData}
              yAxisText="beats per minute"
              xAxisText="subjects"
              stepSize={10}
              datasetLabel='Heart rate'
          />
          <BarChart
              title={"Student's attention chart"}
              labels={subjects}
              userData={studentAttentionData}
              yAxisText="attention"
              xAxisText="subjects"
              datasetLabel="Student's attention"
          />
        </div> :
          <div className="flex flex-col space-y-5">
            <div className="flex flex-col items-center space-y-5">
              <p className="text-3xl text-neutral-300">Class attention</p>
              <div className="w-60 pb-10">
                <FormControl fullWidth autoFocus focused={true}>
                  <InputLabel id="demo-simple-select-label">Class</InputLabel>
                  <Select
                      labelId="demo-simple-select-label"
                      id="demo-simple-select"
                      value={clas}
                      label="Class"
                      onChange={(event) => setClas(event.target.value)}
                  >
                    {
                      classes.map((clas, index) => (
                        <MenuItem key={`menu-item-${index}`} value={clas}>{clas}</MenuItem>
                      ))
                    }
                  </Select>
                </FormControl>
              </div>
            </div>
            <div className="flex flex-row pb-10">
              <Grid container spacing={10}>
                <Grid item>
                  <MetricsCard title="this month" attention={metricThisMonth}/>
                </Grid>
                <Grid item>
                  <MetricsCard title="this week" attention={metricThisWeek}/>
                </Grid>
                <Grid item>
                  <MetricsCard title="today" attention={metricThisDay}/>
                </Grid>
              </Grid>
            </div>
            <div className="flex items-center space-x-20">
              {
                students.length > 0 ?
                  <ItemsList list={students}/> :
                  <div style={{ width: '100%', height: 300, maxWidth: 320 }} className="flex justify-center items-center bg-neutral-800 rounded-lg p-2">
                    <p className="text-xl text-neutral-300 text-center">Select class to view students and statistics!</p>
                  </div>
              }
              <div className="w-full">
                <BarChart
                    title={""}
                    labels={attentionDefaultValues}
                    userData={classAttention}
                    yAxisText="Number of students"
                    xAxisText="attention"
                    datasetLabel="Students paying attention"
                />
              </div>
            </div>
          </div>
      }
    </main>
  );
}
