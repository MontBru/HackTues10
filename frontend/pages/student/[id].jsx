import Navbar from "@/components/Navbar";
import LinearChart from "@/components/LinearChart";
import {Fab} from "@mui/material";
import Grid from "@mui/material/Grid";
import MetricsCard from "@/components/MetricsCard";
import {useState} from "react";
import ChangeEvalPopup from "@/components/ChangeEvalPopup";
import EditIcon from "@mui/icons-material/Edit";

export default function StudentPage({student}) {
    const [showPopup, setShowPopup] = useState(false);
    const mockData = [{subjectName:"Maths", entryIds: [5,1], attention: 5},{subjectName:"BEL", entryIds: [2,4], attention: 6}, {subjectName:"AE", entryIds: [3,6,7], attention: 4}]

    return (
        <main
            className={`flex min-h-screen flex-col items-center bg-neutral-900 px-10 space-y-10`}
        >
            <Navbar isStudent={false}/>
            <ChangeEvalPopup isOpen={showPopup} onClose={()=>{setShowPopup(false)}} evaluations={mockData}/>
            <Fab aria-label="edit" onClick={()=>{setShowPopup(true)}} className="absolute bottom-10 right-10">
                <EditIcon color="primary" />
            </Fab>
            <div className="flex flex-col space-y-5">
                <p className="flex justify-center text-3xl text-neutral-300 pb-5">{student.name}&apos;s attention</p>
                <div className="flex flex-row pb-10">
                <Grid container spacing={10}>
                        <Grid item>
                            <MetricsCard title="this month" attention={student.attentionMonth}/>
                        </Grid>
                        <Grid item>
                            <MetricsCard title="this week" attention={student.attentionWeek}/>
                        </Grid>
                        <Grid item>
                            <MetricsCard title="today" attention={student.attentionToday}/>
                        </Grid>
                    </Grid>
                </div>
                <div className="flex items-center space-x-20">
                    <div className="w-full">
                        <LinearChart
                            title={"This year"}
                            labels={student.attentionYearLabels}
                            userData={student.attentionYearData}
                            yAxisText="attention"
                            xAxisText="time"
                            datasetLabel="Students paying attention"
                            stepSize={1}
                        />
                    </div>
                </div>
            </div>
        </main>
    );
}

export async function getServerSideProps({params}) {
    return {
        props: {
            student: {
                name: "Nikola Petrov",
                clas: "12 V",
                attentionMonth: 7,
                attentionWeek: 6,
                attentionToday: 7,
                attentionYearLabels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
                attentionYearData: [9, 4, 6, 7, 8, 5, 3, 4, 6, 7],
            },
        }
    }
    // return {
    //     notFound: true,
    // }
}