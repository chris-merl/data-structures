#include <iostream>
#include <stack>
#include <string>

struct Bracket {
    Bracket(char type, int position):
        type(type),
        position(position)
    {}

    bool Matchc(char c) {
        if (type == '[' && c == ']')
            return true;
        if (type == '{' && c == '}')
            return true;
        if (type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
};

int main() {
    std::string text;
    std::cout << "Enter the text: ";
    std::getline(std::cin, text);
    int val = 0;
    std::string res;

    std::stack <Bracket> a;
    
    for (int position = 0; position < text.length(); ++position) {
        char next = text[position];

        if (next == '(' || next == '[' || next == '{') {
            a.push(Bracket(next, position));
        }
        if (next == ')' || next == ']' || next == '}') {
            if(a.empty() || !a.top().Matchc(next)){
                val = position + 1;
                std::cout << val;
                break; 
            }else{
                a.pop();
                res = "Success";
                std::cout << res;
                break;
            }
        }
        
        
        
        
    }

   if(!a.empty() && val==0 && res!="Success"){
            val = a.top().position + 1;
            std::cout << val;
    }
    
    return 0;
}
