<template>
    <div class="calculator_grid">
        <div class="output">
            <div class="previous-operand">{{ previous }}</div>
            <div class="current-operand">{{ e === "ERROR" ? e : current || '0'}}</div>
        </div>

        <button @click="operator"  class="operation" value="mod">%</button>
        <button @click="clearCurrent" class="operation">CE</button>
        <button @click="clearAll" class="operation">C</button>
        <button @click="deleteNumber" class="operation">&#9003;</button>

        <button @click="operator" class="operation" value="inverse">1/x</button>
        <button @click="operator"  class="operation" value="square">x2</button>
        <button @click="operator"  class="operation" value="sqrt">2√x</button>
        <button @click="operator"  class="operation" value="÷">÷</button>

        <button @click="append('7')">7</button>
        <button @click="append('8')">8</button>
        <button @click="append('9')">9</button>
        <button @click="operator"  class="operation" value="*">*</button>

        <button @click="append('4')">4</button>
        <button @click="append('5')">5</button>
        <button @click="append('6')">6</button>
        <button @click="operator"  class="operation" value="-">-</button>

        <button @click="append('1')">1</button>
        <button @click="append('2')">2</button>
        <button @click="append('3')">3</button>
        <button @click="operator"  class="operation" value="+">+</button>

        <button @click="sign" class="operation"><sup>+</sup>/<sub>-</sub></button>
        <button @click="append('0')">0</button>
        <button @click="dote" class="operation">.</button>
        <button @click="equal" id="equal" value="=">=</button>
    </div>
</template>

<script>
export default {
    name : 'Calculator-',
    data(){
        return{
            current : '',
            previous : '',
            operation : '',
            e : ''
        }
    },
    methods : {
        async getValue(){
            const url = `http://localhost:8080/${this.operation}`
            const req = await fetch(url)
            const res = await req.text()
            return await res
        },
        clearCurrent(){
            this.e = ''
            this.current = ''
            console.log(this.current)
        },
        clearAll(){
            this.e = ''
            this.current = ''
            this.previous = ''
            console.log(this.current)
        },
        deleteNumber(){
            this.e = ''
            this.current = this.current.substring(0, this.current.length - 1)

            console.log(this.current)
        },
        sign(){
            this.e = ''
            if(this.current !== '')
                this.current = this.current.charAt(0) === '-' ? this.current.slice(1) : `-${this.current}`
            console.log(this.current)
        },
        dote(){
            this.e = ''
            if(this.current === '')
                this.current = `0.`
            else
                this.current = this.current.indexOf('.') === -1 ? `${this.current}.` : this.current
            console.log(this.current)
        },
        append(number){
            this.e = ''
            this.current = this.current === '0' || this.current === '-0' ?  this.current : `${this.current}${number}`
            console.log(this.current)
        },
        async operator(event){
            this.e = ''
            if(this.current !== '' && this.previous === ''){
                this.operation = `${this.current} ${event.target.value}`
                if(event.target.value === 'inverse' || event.target.value === 'square' || event.target.value === 'sqrt'){
                    this.current = await this.getValue()
                    this.previous = ''
                    this.operation = ''
                }
                else{
                    this.previous = `${this.current} ${event.target.textContent}`
                    this.current = ''
                }
                console.log(this.previous)
                console.log(this.operation)
            }
            else if(this.current !== '' && this.previous !== '' && !(event.target.value === 'inverse' || event.target.value === 'square' || event.target.value === 'sqrt')){
                this.operation = `${this.operation} ${this.current}`
                const result = await this.getValue()
                if(result === "ERROR"){
                    this.e = result
                    this.previous = ''
                    this.current = ''
                }
                else{
                    this.previous = `${result} ${event.target.textContent}`
                    this.operation = `${result} ${event.target.value}`
                    this.current = ''
                }
                console.log(this.previous)
                console.log(this.operation)
            }
        },
        async equal(){
            this.e = ''
            if(this.current !== '' && this.previous !== ''){
                this.operation = `${this.operation} ${this.current}`
                const result = await this.getValue()
                if(result === "ERROR")
                    this.e = result
                else
                    this.current = result
                this.previous = ''  
                this.operation = ''
                console.log(this.previous)
                console.log(this.operation)
            }
        }
    }
}
</script>

<style>
@font-face {
    font-family: ARCADECLASSIC;
    src: url(../assets/fonts/ARCADECLASSIC.TTF);
}

*, *::before, *::after{
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    scroll-behavior: smooth;
    font-family: ARCADECLASSIC, sans-serif;
    margin: 0;
    padding: 0;
    font-weight: normal;
    color: #2d2b29;
}

:root{
    --border_color: #3e3e40;
}

.calculator_grid{
    max-width: max-content;
    padding: 30px 20px;
    border-radius: 35px;
    border-width: 3px 7px 5px 3px;
    border-style: ridge;
    border-color: var(--border_color);
    background-color: white;
    box-shadow: 3px 3px 10px black;
    display: grid;
    justify-content: center;
    align-content: center;
    grid-template-columns: repeat(4, 60px);
    grid-template-rows: minmax(150px, auto) repeat(6, 60px);
    gap: 10px 5px;
}

.calculator_grid > button{
    text-align: center;
    cursor: pointer;
    font-size: 1.2em;
    outline: none;
    background-color: #e9e9f5;
    border-radius: 12px;
    border-width: 3px 7px 5px 3px;
    border-style: ridge;
    border-color: var(--border_color);
    box-shadow: 3px 3px 10px black;
}

.calculator_grid .operation{
    background-color: #fb967c;
}
.calculator_grid #equal{
    background-color: #61eeb8;
}

.calculator_grid > button:hover{
    background-color: rgba(234, 234, 246, 0.75)
}
.calculator_grid .operation:hover{
    background-color: rgb( 251, 150, 124, 0.75)
}
.calculator_grid #equal:hover{
    background-color: rgb(98, 239, 185, 0.75)
}

.calculator_grid > button:active{
    scale: 0.8;
}
.output{
    grid-column: 1 / -1;
    padding: 10px;
    background-color: #bbddf5;
    border-radius: 20px;
    border-width: 3px 7px 5px 3px;
    border-style: ridge;
    border-color: var(--border_color);
    box-shadow: 3px 3px 10px black;
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    justify-content: flex-end;
    word-wrap: break-word;
    word-break: break-all;
}
.output .previous-operand{
    color: rgba(46, 43, 41, 0.75);
    font-size: 1.5rem;
    margin-bottom: 10px;
}
.output .current-operand{
    font-size: 3rem;
}
</style>