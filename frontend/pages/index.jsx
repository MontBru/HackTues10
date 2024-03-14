import { Rubik } from "next/font/google";
import Navbar from "@/components/Navbar";

const rubik = Rubik({ subsets: ["cyrillic"] });

export default function Home() {
  return (
    <main
      className={`flex min-h-screen flex-col items-center justify-between bg-neutral-900 ${rubik.className}`}
    >
      <Navbar/>
    </main>
  );
}
