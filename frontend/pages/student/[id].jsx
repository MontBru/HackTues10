import Navbar from "@/components/Navbar";
import LinearChart from "@/components/LinearChart";
import {Fab} from "@mui/material";
import Grid from "@mui/material/Grid";
import MetricsCard from "@/components/MetricsCard";
import {Rubik} from "next/font/google";
import {useState} from "react";
import ChangeEvalPopup from "@/components/ChangeEvalPopup";
import EditIcon from "@mui/icons-material/Edit";

const rubik = Rubik({ subsets: ["cyrillic"] });

export default function StudentPage({student}) {
    const tmpStudent = {
        name: "Nikola Petrov",
        clas: "12 V"
    }
    const [clas, setClas] = useState("");
    const isStudent = false;

    const [showPopup, setShowPopup] = useState(false);
    const mockData = [{subjectName:"Maths", entryIds: [5,1], attention: 5},{subjectName:"BEL", entryIds: [2,4], attention: 6}, {subjectName:"AE", entryIds: [3,6,7], attention: 4}]

    return (
        <main
            className={`flex min-h-screen flex-col items-center bg-neutral-900 px-10 space-y-10 ${rubik.className}`}
        >
            <Navbar isStudent={isStudent}/>
            <ChangeEvalPopup isOpen={showPopup} onClose={()=>{setShowPopup(false)}} evaluations={mockData}/>
            <Fab aria-label="edit" onClick={()=>{setShowPopup(true)}} className="absolute bottom-10 right-10">
                <EditIcon color="primary" />
            </Fab>
            <div className="flex flex-col space-y-5">
                <p className="flex justify-center text-3xl text-neutral-300 pb-5">{tmpStudent.name}'s attention</p>
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
                    <div className="w-full">
                        <LinearChart
                            title={"This year"}
                            labels={[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}
                            userData={[9, 4, 6, 7, 8, 5, 3, 4, 6, 7]}
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
            student: params.id,
        }
    }
    // return {
    //     notFound: true,
    // }
}