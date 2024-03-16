import Card from "@/components/Card";
import Grid from "@mui/material/Grid";
import Navbar from "@/components/Navbar";
import ItemsList from "@/components/ItemsList";
import {useEffect, useState} from "react";
import Box from "@mui/material/Box";
import {CircularProgress} from "@mui/material";
import { getLiveUserAttention } from "@/services/User/LiveUserAttention";

export default function Live() {
    const [subject, setSubject] = useState("");
    const [period, setPeriod] = useState("");
    const [students, setStudents] = useState([]);
    const [studentsNoInterest, setStudentsNoInterest] = useState([]);
    const [clas, setClas] = useState("12 v")

    useEffect(() => {
        const fetchData = async () => {
            setSubject("English");
            setPeriod("9:30 - 10:50");
            setStudents(["Nikola Petrov 12V", "Ivan Postolov 12V", "Bryan Monticelli 12V", "Stefan Georgiev 11V", "Kaloyan Sotirov 12V", "Nikola Petrov 12V", "Ivan Postolov 12V", "Bryan Monticelli 12V", "Stefan Georgiev 11V", "Kaloyan Sotirov 12V"]);
            const [klas, grade] = clas.split(" ");
            const NOinterestedUsers = await getLiveUserAttention(klas, grade)
            NOinterestedUsers.filter((user)=>{
                return user.evaluation !== null && user.evaluation !== undefined;
            })
            console.log(NOinterestedUsers)
            setStudentsNoInterest(NOinterestedUsers);
        }

        const interval = setInterval(() => {
            fetchData();
        }, 60000); //timeout is in miliseconds

        fetchData();
        return () => clearInterval(interval);
    }, [])

    if(subject === "" || period === ""){
        return (
            <div className="flex justify-center items-center min-h-screen bg-neutral-900">
                <Box sx={{ display: 'flex', color: 'grey.500' }}>
                    <CircularProgress color="inherit" />
                </Box>
            </div>
        );
    }

    return (
        <main className="min-h-screen bg-neutral-900 px-10 space-y-10 pb-20">
            <Navbar isStudent={false} islive={true}/>
            <div className={`flex flex-col items-center`}>
                <div className="flex flex-col space-y-2 items-center justify-center">
                    <p className="flex justify-center text-4xl text-neutral-300">{subject}</p>
                    <p className="text-2xl text-neutral-300">{period}</p>
                </div>
            </div>
            <div className="flex justify-center space-x-20">
                <div className="flex flex-col space-y-5">
                    <p className="text-2xl text-neutral-300">Students:</p>
                    <ItemsList list={students}/>
                </div>
                <div className="space-y-5">
                    <p className="text-2xl text-neutral-300">No interest:</p>
                    <div>
                   {
                     studentsNoInterest == null &&
                        <Grid container columns={2} spacing={5} style={{width: '720px'}}>
                           
                            {
                            
                                studentsNoInterest.map((student, index) => (
                                    <Grid key={`grid-item-${index}`} item xs={1}>
                                        <Card title={student.name} attention={student.attention}/>
                                    </Grid>
                                ))
                            }
                            {/*<Grid item xs={1}>*/}
                            {/*    <Card title={"Nikola Petrov"} attention={3}/>*/}
                            {/*</Grid>*/}
                            {/*<Grid item xs={1}>*/}
                            {/*    <Card title={"Ivan Postolov"} attention={3}/>*/}
                            {/*</Grid>*/}
                            {/*<Grid item xs={1}>*/}
                            {/*    <Card title={"Nikola Petrov"} attention={3}/>*/}
                            {/*</Grid>*/}
                            {/*<Grid item xs={1}>*/}
                            {/*    <Card title={"Ivan Postolov"} attention={3}/>*/}
                            {/*</Grid>*/}
                            {/*<Grid item xs={1}>*/}
                            {/*    <Card title={"Ivan Postolov"} attention={3}/>*/}
                            {/*</Grid>*/}
                        </Grid>
                   } </div>
                </div>
            </div>
        </main>
    );
}