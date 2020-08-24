import subprocess

from flask import Flask, request

app = Flask("Natbag2020")


def allflights(direction):
    return subprocess.check_output(["java", "-classpath",
                                    r"C:\Users\יובל\eclipse-workspace\Nattbag2020\Natbag2020\bin", "core.Program",
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
