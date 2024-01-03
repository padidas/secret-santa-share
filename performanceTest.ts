const path: string = Deno.args[0] ?? "ideas";
const amount = Deno.args[1] ? Number(Deno.args[1]) : 1_000;

const MY_URL = `http://localhost:8080/${path}`;

const responseTimes: number[] = [];
let successes = 0
let fails = 0
const DEF_OF_SLOW = .5

const promises = [...Array(amount).keys()].map(async (_, i) => {
    const start = Date.now()
    const res = await fetch(MY_URL)
    if (i == 3) console.log(await res.json())
    responseTimes.push((Date.now() - start) / 1000)
    return res;
})

Promise.allSettled(promises).then((results) => {
    results.forEach((res, i) => {
        if (res.status === "fulfilled") successes++
        else fails++


    })
    const max = responseTimes.reduce((a, b) => {
        if (a >= b) return a
        return b
    }, 0)
    const min = responseTimes.reduce((a, b) => {
        if (a <= b) return a
        return b
    }, 999999)
    const average = responseTimes.reduce((a, b) => {
        return a + b
    }, 0) / responseTimes.length
    const slowResults = responseTimes.filter(rT => rT > DEF_OF_SLOW).length

    console.log("")
    console.log(`${amount}  x  ${path}`)
    console.log("")
    console.log("MAX:        ", max)
    console.log("MIN:        ", min)
    console.log("AVERAGE:    ", average)
    console.log("SUCCESSFUL: ", successes)
    console.log("FAILED:     ", fails)
    console.log("SLOW:       ", slowResults)
})
