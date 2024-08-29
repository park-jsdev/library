from openai import OpenAI

client = OpenAI(api_key="")

def generate_response(input_text):
    response = client.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "system", "content": "You are a helpful assistant."},
            {"role": "user", "content": input_text}
        ]
    )
    message = response.choices[0].message.content
    return message

if __name__ == "__main__":
    print("Hello! What would you like to learn? Type 'x' to quit.")
    while True:
        input_text = input("You: ")
        if input_text.lower() == "x":
            print("Goodbye!")
            break
        response = generate_response(input_text)
        print("GPT:", response)
