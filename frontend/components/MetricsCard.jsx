export default function MetricsCard({title, attention}) {
    return (
        <article className="flex flex-col space-y-2 items-center justify-center bg-neutral-800 rounded-lg w-80 h-36 shadow-lg">
            <p className="text-xl text-neutral-300">{title}</p>
            <p className="text-4xl text-neutral-300">{attention}/10</p>
        </article>
    );
}