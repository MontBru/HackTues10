import { Rubik } from "next/font/google";
import Card from "@/components/Card";
import Grid from "@mui/material/Grid";
import ListElement from "@/components/ListElement";
import Navbar from "@/components/Navbar";
import ItemsList from "@/components/ItemsList";

const rubik = Rubik({ subsets: ["cyrillic"] });

export default function Live()
{
    return (
        <main className="min-h-screen bg-neutral-900 px-10 space-y-10 pb-20">
            <Navbar isStudent={false} islive={true}/>
            <div className={`flex  flex-col items-center ${rubik.className}`}>
                <div className="flex flex-col space-y-2 items-center justify-center">
                    <text className="flex justify-center text-4xl text-neutral-300">English</text>
                    <p className="text-2xl text-neutral-300">9:30 - 10:50</p>
                </div>
            </div>
            <div className="flex justify-center space-x-20">
                <div className="space-y-5">
                    <text className="text-2xl text-neutral-300">Students:</text>
                    <ItemsList
                        list={["Nikola Petrov 12V", "Ivan Postolov 12V", "Bryan Monticelli 12V", "Stefan Georgiev 11V", "Kaloyan Sotirov 12V", "Nikola Petrov 12V", "Ivan Postolov 12V", "Bryan Monticelli 12V", "Stefan Georgiev 11V", "Kaloyan Sotirov 12V"]}/>
                </div>
                <div className="space-y-5">
                    <text className="text-2xl text-neutral-300">No interest:</text>
                    <div className="flex flex-row items-center justify-center">
                        <Grid container columns={2} spacing={10}>
                            <Grid item>
                                <Card title={"Nikola Petrov"} attention={3}/>
                            </Grid>
                            <Grid item>
                                <Card title={"Ivan Postolov"} attention={3}/>
                            </Grid>
                            <Grid item>
                                <Card title={"Nikola Petrov"} attention={3}/>
                            </Grid>
                            <Grid item>
                                <Card title={"Ivan Postolov"} attention={3}/>
                            </Grid>
                            <Grid item>
                                <Card title={"Nikola Petrov"} attention={3}/>
                            </Grid>
                            <Grid item>
                                <Card title={"Ivan Postolov"} attention={3}/>
                            </Grid>
                            <Grid item>
                                <Card title={"Nikola Petrov"} attention={3}/>
                            </Grid>
                            <Grid item>
                                <Card title={"Ivan Postolov"} attention={3}/>
                            </Grid>
                        </Grid>
                    </div>
                </div>
            </div>
        </main>
    );
}