from flask import Flask, render_template, Response, request
import time
import ast
import json
import requests
import parse
app = Flask(__name__)


@app.route("/send_email", methods=['POST'])
def send_msg():
    checklist = request.json.get("checklist",None)
    user_email = request.json.get("to", "abc@gmail.com")
    response = {}
    if checklist:
        checklist_response_status = parse.send_checklist(checklist,user_email)
        if checklist_response_status:
            response["checklist"] = 200
    
    transcript = request.json.get("transcript",None)
    if transcript:
        transcript_status = parse.send_transcript(transcript,user_email)
        if transcript_status:
            response["transcript"] = 200
    
    form_fields = request.json.get("form",None)
    if form_fields:
        form_fill_status = parse.parse_form_fields(form_fields, user_email)
        if form_fill_status:
            response["form_fill"] = 200
    
    return Response(json.dumps(response), status=200, mimetype="application/json")



if __name__ == "__main__":
	app.run('localhost', port=8000)
