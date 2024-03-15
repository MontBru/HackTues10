import Navbar from "@/components/Navbar";
import DatePickerField from "@/components/DatePickerField";
import LinearChart from "@/components/LinearChart";
import BarChart from "@/components/BarChart";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";
import Grid from "@mui/material/Grid";
import MetricsCard from "@/components/MetricsCard";
import ItemsList from "@/components/ItemsList";
import {Rubik} from "next/font/google";
import {useState} from "react";

const rubik = Rubik({ subsets: ["cyrillic"] });

export default function StudentPage({student}) {
    const tmpStudent = {
        name: "Nikola Petrov",
        clas: "12 V"
    }
    const [clas, setClas] = useState("");
    const isStudent = false;

    return (
        <main
            className={`flex min-h-screen flex-col items-center bg-neutral-900 px-10 space-y-10 pb-20 ${rubik.className}`}
        >
            <Navbar isStudent={isStudent}/>
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
                            userData={[100, 75, 65, 90, 110, 130, 70, 60, 140, 80]}
                            yAxisText="attention"
                            xAxisText="time"
                            datasetLabel="Students paying attention"
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