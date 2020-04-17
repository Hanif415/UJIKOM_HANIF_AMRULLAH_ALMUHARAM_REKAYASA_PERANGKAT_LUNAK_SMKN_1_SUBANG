<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use App\Petugas;

class Level extends Model
{
    public function users()
    {
        return $this->hasMany(User::class);
    }
}
