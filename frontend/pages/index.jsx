import { Rubik } from "next/font/google";
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
import useUserStore from "@/Storages/UserStorage";
import { getSubClassAVG } from "@/services/SubClass/SubClassAVG";

const rubik = Rubik({ subsets: ["cyrillic"] });

export default function Home() {
  const [clas, setClas] = useState("");
  const [date, setDate] = useState("");
  const [metricThisMonth, setMetricThisMonth] = useState(0);
  const [metricThisWeek, setMetricThisWeek] = useState(0);
  const [metricThisDay, setMetricThisDay] = useState(0);
  const [isStudent, setIsStudent] = useState(null);
  const [pulseData, setPulseData] = useState([]);
  const [studentAttentionData, setStudentAttentionData] = useState([]);
  const [subjects, setSubjects] = useState([]);
  const [classes, setClasses] = useState([]);
  const [classAttention, setClassAttention] = useState([]);
  const attentionDefaultValues = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const {me, setMe} = useUserStore(); 

  useEffect(() => {
    
    const fetch = async () => {
      
      const metricThisMonthAVG = await getSubClassAVG(clas, "month")
      setMetricThisMonth(metricThisMonthAVG);
      
      const metricThisWeekAVG = await getSubClassAVG(clas, "week")
      setMetricThisWeek(metricThisWeekAVG);
      
      const metricThisDayAVG = await getSubClassAVG(clas, "day")
      setMetricThisDay(metricThisDayAVG);
      if(me.role === 0)
      {
        setIsStudent(false);
      }
      else
      {
        setIsStudent(true)
      }
      
      setPulseData([100, 75, 65, 90, 110, 130, 70]);
      setStudentAttentionData([5, 8, 6, 9, 7, 4, 5]);
      setSubjects(['English', "Maths", "VOT", "IOT", "Biology", "Chemistry", "History"]);
      setClasses(["12 v", "11 V", "12 A", "10 G"]);
      setClassAttention([5, 0, 8, 6, 9, 7, 0, 4, 5, 0]);
    }
    fetch()
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
      className={`flex min-h-screen flex-col items-center bg-neutral-900 px-10 space-y-10 pb-20 ${rubik.className}`}
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
                      classes.map((clas) => (
                        <MenuItem value={clas}>{clas}</MenuItem>
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
              <ItemsList list={["Nikola Petrov 12V", "Ivan Postolov 12V", "Bryan Monticelli 12V", "Stefan Georgiev 11V", "Kaloyan Sotirov 12V", "Nikola Petrov 12V", "Ivan Postolov 12V", "Bryan Monticelli 12V", "Stefan Georgiev 11V", "Kaloyan Sotirov 12V"]}/>
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
