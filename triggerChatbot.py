import json

""" Parses JSON of viable chat triggers and returns
    list of lists of synonyms of possiblilities
    -- eg. ['Weather', 'Temperature']--
    and a list of the corresponding query
    -- eg. do weather query """
def parseJSON():
    file = open("functionality.json", "r")
    fileRead = file.read()
    parsed_json = json.loads(fileRead)

    syn = []

    #print(parsed_json["entries"][0]["synonyms"])

    for synList in parsed_json["entries"]:
        #print(synList["synonyms"])
        syn.append(synList["synonyms"])

    values = []
    for val in parsed_json["entries"]:
        #print(val["value"])
        values.append(val["value"])
    

    return syn, values

""" Takes user's message and determines if it contains @chatbot
    --eg. if chatbot is needed--
    and returns this boolean"""
def chatbotCheck(msg):
    return "@chatbot" in msg

""" Takes user's message and searches for any synonym
    --eg. if chatbot query is viable--
    and returns the query value or an empty string"""
def synCheck(msg, synonyms, values):
    #print(values)

    for i in range(len(values)):
        for synonym in synonyms[i]:
            if synonym in msg:
                return values[i]
    return ""
        

""" Given a user message, return either an empty string
    (no chatbot query to do) or the query value (eg.
    "weather") """
def chatbot(msg):
    synonyms, qValues = parseJSON()

    if chatbotCheck(msg):
        return synCheck(msg, synonyms, qValues)
    else:
        return ""

##quick tests:
#print(chatbot("@chatbot weather"))
#print(chatbot("@chatbot klsdfj sjldfl"))
#print(chatbot("@chatbot"))
#print(chatbot("weather"))
#print(chatbot("jflskdjs fjlskdfs"))
#print(chatbot(""))

