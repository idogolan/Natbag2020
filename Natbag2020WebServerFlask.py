import subprocess

from flask import Flask, request

app = Flask("Natbag2020")


def allflights(direction): #need to fix the address problem
    return subprocess.check_output(["java", "-classpath",
                                    r"C:\Users\Roman Michailov\OneDrive\Object Oriented Programming\Development Tools Course\Natbag2020-git\Natbag2020\bin", "core.Program",
                                    request.args.get('outformat'), direction,  # check if departures is args[1]
                                    request.args.get('company'), request.args.get('country'),
                                    request.args.get('day1'), request.args.get('month1'),
                                    request.args.get('year1'), request.args.get('day2'),
                                    request.args.get('month2'), request.args.get('year2'),
                                    request.args.get('sunday'), request.args.get('monday'),
                                    request.args.get('tuesday'), request.args.get('wednesday'),
                                    request.args.get('thursday'), request.args.get('friday'),
                                    request.args.get('saturday')])


@app.route("/departures")
def depart():
    return allflights("departures")


@app.route("/arrivals")
def arrival():
    return allflights("arrivals")


app.run(port=8000, host="0.0.0.0")



# http://localhost:8000/departures?outformat=html&company=El-Al&country=BARCELONA&day1=2&month1=2&year1=2019&day2=5&month2=12&year2=2020&sunday=true&monday=true&tuesday=true&wednesday=true&thursday=true&friday=true&saturday=true
# http://localhost:8000/arrivals?outformat=html&company=El-Al&country=ISRAEL&day1=20&month1=1&year1=2019&day2=5&month2=11&year2=2020&sunday=true&monday=true&tuesday=true&wednesday=true&thursday=true&friday=true&saturday
