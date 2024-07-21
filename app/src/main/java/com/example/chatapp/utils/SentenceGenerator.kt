package com.example.chatapp.utils

class SentenceGenerator {
    companion object {
        private val sentences = listOf(
            "Hey! How's everything going on your end?",
            "Hi there! Long time no see.",
            "Hello! How have you been doing lately?",
            "Yo! What's been keeping you busy?",
            "Howdy! How's life treating you?",
            "How are you holding up with all the work?",
            "What's up? Any new exciting plans?",
            "How's it going? Did you finish that project?",
            "What's new? Anything interesting happening?",
            "How have you been? Everything good on your side?",
            "I'm good, thanks! Just trying to keep up with the workload.",
            "Not much, you? Just the usual routine.",
            "Doing well! Finally got some time to relax.",
            "Same old, same old. Just trying to stay productive.",
            "I've been better, but managing. How about you?",
            "Alright, I gotta go now. Catch up later!",
            "It was nice talking to you! Let's do this again soon.",
            "Gotta run, talk to you later!",
            "Take care! We'll chat again soon.",
            "I have to go, but it was great catching up!"
        )

        fun generate(): String {
            return sentences.random()
        }
    }
}